'use strict';

import React, {Component} from 'react';

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.setEmail = this.setEmail.bind(this);
    this.setPassword = this.setPassword.bind(this);
    this.setMessage = this.setMessage.bind(this);
    this.setTradersOnline = this.setTradersOnline.bind(this);
    this.sendLoginInfo = this.sendLoginInfo.bind(this);

    this.state = {
      email: '',
      password: '',
      message: '',
      tradersOnline: '',
    };
  }

  setEmail(event) {
    this.setState({email: event.target.value});
  }

  setPassword(event) {
    this.setState({password: event.target.value});
  }

  setMessage(message) {
    this.setState({message: message});
  }

  setTradersOnline(count) {
    this.setState({tradersOnline: count});
  }

  // Send the email and password as JSON to the backend
  sendLoginInfo(event) {
    fetch('/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: this.state.email,
        password: this.state.password
      })
    }).then(res => {
      // Update the page's message with the server's response
      res.text().then(text => {
        this.setMessage(text);
      })
    });
  }

  componentDidMount() {
    this.setTradersOnline(0);
  }

  render() {
    return (
      <div id="entry">
        <div id="brand">
          <img id="doge" src="public/img/doge.png" />
          <img id="logo" src="public/img/logo.png" />
        </div>

        <div id="motto">PRAISE KEK. TRADE KEK.</div>

        <div id="entry-message">{this.state.message}</div>

        <div id="entry-form">
          <input
            className="entry-field"
            type="text"
            placeholder="EMAIL"
            name="email"
            onChange={this.setEmail}
            required
          />

          <input
            className="entry-field"
            type="password"
            placeholder="PASSWORD"
            name="password"
            onChange={this.setPassword}
            required
          />

          <div id="login-form-buttons">
            <input
              type="submit"
              value="LOGIN"
              onClick={this.sendLoginInfo}
            />

            <a href="/signup">
              SIGN UP
            </a>
          </div>
        </div>

        <div id="traders-online">
          TRADER'S ONLINE: {this.state.tradersOnline}
        </div>

        <div id="trending-terminal">TRENDING TERMINAL</div>
      </div>
    );
  }
}