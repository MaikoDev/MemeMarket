'use strict';

import React, {Component} from 'react';

export default class Login extends Component {
  render() {
    return (
      <div id="login">
        <div id="brand">
          <img id="doge" src="public/img/doge.png"/>
          <img id="logo" src="public/img/logo.png" />
        </div>

        <div id="motto">Praise Kek. Trade Kek.</div>

        <div id="credentials">
          <div id="credentials-input">
            <div>
              <input type="text" placeholder="Username/Email" name="login" required />
            </div>

            <div>
              <input type="password" placeholder="Password" name="password" required />
            </div>
          </div>

          <div id="credentials-buttons">
            <div>
              <input type="button" value="Login"/>
            </div>

            <div>
              <input type="button" value="Register"/>
            </div>
          </div>
        </div>

        <div id="traders-online">Trader's Online: 54, 287</div>

        <div id="trending-terminal">
          Trending Terminal
        </div>
      </div>
    );
  }
}