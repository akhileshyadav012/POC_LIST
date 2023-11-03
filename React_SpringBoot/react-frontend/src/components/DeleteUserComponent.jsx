import React, { Component } from 'react';
import UserService from '../services/UserService';
import { Link,useParams,useNavigate } from "react-router-dom";


class DeleteUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            userId: this.props.id,
            name: '',
            email: '',
            username: '',
            password: '',
            role: ''
        }
        this.changeUserIdHandler = this.changeUserIdHandler.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeEmailNameHandler = this.changeEmailNameHandler.bind(this);
        this.changeUserNameHandler = this.changeUserNameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.changeRoleHandler = this.changeRoleHandler.bind(this);
        this.deleteUser = this.deleteUser.bind(this);

    }

    componentDidMount(){
        console.log("id = " + this.state.userId)
        UserService.getUserById(this.state.userId).then( (res) =>{
            let user = res.data;
            console.log("data = " + res.data);
            this.setState({
                name: user.name,
                email: user.email,
                username: user.username,
                password: user.password,
                role: user.role
            });
        });
    }

    deleteUser = (e) =>{  
        e.preventDefault();
        let user = {userId: this.state.userId, name: this.state.name, email: this.state.email,
        username: this.state.username, password: this.state.password, role: this.state.role};
        console.log('user => ' + JSON.stringify(user));

        UserService.deleteUserById(this.state.userId).then( res =>{
            this.props.navigate('/users');
        });
    }

    changeUserIdHandler= (event) =>{
        this.setState({userId: event.target.value});
    }
    changeNameHandler= (event) =>{
        this.setState({name: event.target.value});
    }
    changeEmailNameHandler= (event) =>{
        this.setState({email: event.target.value});
    }
    changeUserNameHandler= (event) =>{
        this.setState({username: event.target.value});
    }
    changePasswordHandler= (event) =>{
        this.setState({password: event.target.value});
    }
    changeRoleHandler= (event) =>{
        this.setState({role: event.target.value});
    }

    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3 offset-md-3'>
                            <h3 className='text-center'> Delete User</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> UserId: </label>
                                        <input placeholder='UserId' name='userId' className='form-control' 
                                        value={ this.state.userId } onChange={this.changeUserIdHandler} />
                                    </div>

                                    <div className='form-group'>
                                        <label> Name: </label>
                                        <input placeholder='Name' name='name' className='form-control' 
                                        value={ this.state.name } onChange={this.changeNameHandler} />
                                    </div>

                                    <div className='form-group'>
                                        <label> Email: </label>
                                        <input placeholder='Email' name='email' className='form-control' 
                                        value={ this.state.email } onChange={this.changeEmailNameHandler} />
                                    </div>

                                    <div className='form-group'>
                                        <label> Username: </label>
                                        <input placeholder='Username' name='username' className='form-control' 
                                        value={ this.state.username } onChange={this.changeUserNameHandler} />
                                    </div>

                                    <div className='form-group'>
                                        <label> Password: </label>
                                        <input placeholder='Password' name='password' className='form-control' 
                                        value={ this.state.password } onChange={this.changePasswordHandler} />
                                    </div>

                                    <div className='form-group'>
                                        <label> Role: </label>
                                        <input placeholder='Role' name='role' className='form-control' 
                                        value={ this.state.role } onChange={this.changeRoleHandler} />
                                    </div>

                                    <button className='btn btn-success' onClick={this.deleteUser}>Delete</button>
                                    <Link to="/users">
                                    <button className='btn btn-danger' style={{marginLeft: "10px"}}>Cancel</button>
                                    </Link>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

function DeleteUserWrapper(){
    const{id}=useParams()
    const navigate=useNavigate()
    return <DeleteUserComponent id={id} navigate={navigate}/>
}

export default DeleteUserWrapper;