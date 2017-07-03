'use strict';

import React, {Component} from 'react';

export default class Signup extends Component {
  constructor(props) {
    super(props);

    this.setUsername = this.setUsername.bind(this);
    this.setEmail = this.setEmail.bind(this);
    this.setPassword = this.setPassword.bind(this);
    this.setMessage = this.setMessage.bind(this);
    this.sendSignupInfo = this.sendSignupInfo.bind(this);

    this.state = {
      username: '',
      email: '',
      password: '',
      message: ''
    };
  }

  setUsername(event) {
    this.setState({username: event.target.value});
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

  // Send the signup information as JSON to the server
  sendSignupInfo(event) {
    fetch('/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: this.state.username,
        email: this.state.email,
        password: this.state.password
      })
    }).then(res => {
      // Update the page's message with the server's response
      res.text().then(text => {
        this.setMessage(text);
      });
    });
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
            placeholder="USERNAME"
            name="username"
            onChange={this.setUsername}
            required
          />

          <input
            className="entry-field"
            type="email"
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

          <div id="signup-form-buttons">
            <a href="/">
              BACK
            </a>

            <input
              type="submit"
              value="SIGN UP"
              onClick={this.sendSignupInfo}
            />
          </div>
        </div>
      </div>
    );
  }
}