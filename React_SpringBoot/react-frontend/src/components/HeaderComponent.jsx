import React, { Component } from 'react';

class HeaderComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            
        }

    }

    render() {
        const redText = { color: 'red' };
      
        return (
          <div>
            <header>
              <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                <div> <a href="https://youtube.com/shorts/S6k1C57j8yc?si=7E1Y7U-4f7QgB7P_" className='navbar-brand' style={redText}> User Management App </a> </div>
              </nav>
            </header>
          </div>
        );
      }
}

export default HeaderComponent;