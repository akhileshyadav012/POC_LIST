import React, { Component } from 'react';
import swal from 'sweetalert';
import OrderService from '../service/OrderService';
import axios from 'axios';

class BasicForm extends Component {
    constructor(props) {
        super(props)

        this.state = {
            amount: 0
        }
        this.changeAmountHandler = this.changeAmountHandler.bind(this);
        this.handlePayment = this.handlePayment.bind(this);

    }

    changeAmountHandler = (event) => {
        this.setState({amount : event.target.value})
    }
    handlePayment = async() => {
        console.log("Payment Started....")
        // if(this.state.amount = "" || this.state.amount == null){
        //     console.log("enter the amount");
        //     // swal("Failed !!", "Amount is required !!", "error");
        //     console.log("enter the amount");
        //     return;
        // }
        let payment={amount: this.state.amount}
        OrderService.createOrders(payment).then(res =>{
            console.log(res.data);
            const order = res.data;
            console.log("order = " + order);

            const options = {
                key: 'rzp_test_b9oGIC0L3YDw9m',
                amount: order.amount,
                currency: order.currency,
                order_id: order.orderId,
                name: 'Akhilesh',
                description: 'Payment for Service',
                image:"https://images.unsplash.com/photo-1562690868-60bbe7293e94?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cm9zZSUyMGZsb3dlcnxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80",
                handler: function (response) {
                  // Handle successful payment here
                  console.log("payment_id" + response.razorpay_payment_id);
                  console.log("order_id" + response.razorpay_order_id);
                  console.log("signature" + response.razorpay_signature);
                  console.log("Payment Successfull!!!");

                  let updatePaidStatus={orderId: response.razorpay_order_id
                    ,paymentId: response.razorpay_payment_id
                    ,status: "PAID" 
                    ,signature: response.razorpay_signature
                    };

                    axios.post('http://localhost:9091/v1/orders/update',updatePaidStatus)
                    console.log("Order Updated Successfully");
                },
                prefill: {
                  name: '',
                  email: '',
                  contact: '',
                },
                theme: {
                  color: '#F37254',
                },
              };
              let rzp = new window.Razorpay(options);
              rzp.on('payment.failed', function (response) {
              // Handle payment failure here
              console.error(response.error.metadata);
          });
      
          rzp.open();

        });
    }
    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Payment Form</h3>
                            <div className='card-body'>
                                <form>
                                    <input type='text' className='form-control my-2' name='amount' placeholder='Enter amount here' onChange={this.changeAmountHandler}/>

                                    <div className='container text-center mt-3'>
                                        <button type="button" onClick={this.handlePayment} className='btn btn-success'>PAY</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default BasicForm;