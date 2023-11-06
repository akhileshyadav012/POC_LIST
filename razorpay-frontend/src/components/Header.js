import React, { Component } from 'react';
import { Alert } from 'reactstrap';

class Header extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }

    }

    render() {
        return (
            <div >
                <Alert color="primary">
                    <h3 classnName="text-center"> RazorPay - PaymentGateway App!!! </h3>
                </Alert>
            </div>
        );
    }
}

export default Header;