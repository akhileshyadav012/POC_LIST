import React, { Component } from 'react';
import { Link,useParams,useNavigate } from "react-router-dom";
import UserService from '../services/UserService';



class ViewUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            userId: this.props.id,
            user : {}
        }
    }

    componentDidMount(){
        UserService.getUserById(this.state.userId).then( res =>{
            this.setState({user : res.data});
            console.log("data = " + res.data)
        });
    }


    render() {
        return (
            <div>
                <br></br>
                <div className='card col-md-6 offset-md-3'>
                    <h3 className='text-center'>View User Details</h3>
                    <div className='card-body'>
                        
                            <label> UserId: </label>
                             {this.state.user.userId} 
                            <br></br>
                            <label> Name: </label>
                             {this.state.user.name} 
                            <br></br>
                            <label> Email: </label>
                             {this.state.user.emailId} 
                             <br></br>
                            <label> Username: </label>
                             {this.state.user.username} 
                             <br></br>
                            <label> Password: </label>
                             {this.state.user.password} 
                             <br></br>
                            <label> Role: </label>
                             {this.state.user.role} 
                        
                    </div>
                </div>
                <div className='text-center'>
                    <Link to="/users" className="btn btn-primary" style={{marginTop:"10px"}}>
                        Back
                    </Link>
                </div>
            </div>
        );
    }
}
function ViewUserWrapper(){
    const{id}=useParams()
    return <ViewUserComponent id={id}/>
}
export default ViewUserWrapper;