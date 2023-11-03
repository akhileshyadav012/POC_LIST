import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Switch, Routes } from 'react-router-dom'
import ListUserComponent from './components/ListUserComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateUserComponent from './components/CreateUserComponent';
import UpdateUserComponent from './components/UpdateUserComponent';
import DeleteUserComponent from './components/DeleteUserComponent';
import ViewUserComponent from './components/ViewUserComponent';

function App() {
  return (
    <div>
      <Router>
        <div className='container'>
          <HeaderComponent />
          <div className="container">
              <Routes>
                <Route path="/" exact element={<ListUserComponent />}></Route>
                <Route path="/users" element={<ListUserComponent />} ></Route>
                <Route path='/add-users' element={<CreateUserComponent />}></Route>
                <Route path='/update-user/:id' element={<UpdateUserComponent />}></Route>
                <Route path='/delete-user/:id' element={<DeleteUserComponent />}></Route>
                <Route path='/view-users/:id' element={<ViewUserComponent />}></Route>
              </Routes>
          </div>
          <FooterComponent />
        </div>
      </Router>
    </div>
  );
}

export default App;
