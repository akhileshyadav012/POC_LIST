import React, { Component } from 'react';
import UserService from '../services/UserService';
import { BsEyeFill, BsFillPencilFill, BsFillPlusSquareFill, BsFillTrashFill } from 'react-icons/bs';
import { Link } from "react-router-dom";
import { Navigate } from 'react-router-dom';

class ListUserComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users: []
        }
    }

    // deleteUser(userId) {
    //     UserService.deleteUserById(userId).then(res => {
    //         // this.props.navigate('/users');
    //         return <Navigate to="/users" replace={true} />
    //     });

    // }

    componentDidMount() {
        UserService.getEmployees().then((res) => {
            this.setState({ users: res.data });
        });
    }

    render() {
        return (
            <div>
                <h2 className='text-center'>Users List</h2>
                <div>
                    <Link to="/add-users" className="btn btn-primary">
                        <h5 className='text-center'>Add Users</h5>
                    </Link>
                </div>
                <div className='row'>
                    <table className='table table-striped table-bordered'>
                        <thead>
                            <tr>
                                <th>UserId</th>
                                <th>Name</th>
                                <th>EmailId</th>
                                <th>UserName</th>
                                <th>Password</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map(
                                    user =>
                                        <tr key={user.userId}>
                                            <td> {user.userId} </td>
                                            <td> {user.name} </td>
                                            <td> {user.email} </td>
                                            <td> {user.username} </td>
                                            <td> {user.password} </td>
                                            <td> {user.role} </td>
                                            <td>
                                                <Link to={`/update-user/${user.userId}`} className='btn btn-info' >
                                                    <span>Update</span>
                                                </Link>
                                                <Link to={`/delete-user/${user.userId}`} className='btn btn-danger' style={{ marginLeft: "10px" }}>
                                                    <span>Delete</span>
                                                </Link>
                                                {/* <Link to="/users">
                                                    <button style={{ marginLeft: "10px" }} onClick={() => this.deleteUser(user.userId)} className='btn btn-danger'>Delete</button>
                                                </Link> */}
                                                <Link to={`/view-users/${user.userId}`} className='btn btn-success' style={{ marginLeft: "10px" }}>
                                                    <span>View</span>
                                                </Link>
                                            </td>
                                        </tr>
                                )
                            }

                        </tbody>
                    </table>

                </div>

            </div>
        );
    }
}

export default ListUserComponent;