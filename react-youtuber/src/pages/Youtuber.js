import logo from '../logo.svg';
import '../App.css';
import $ from "jquery";
import axios from 'axios';
import data from '../data/data.js';

import React, {useState, useEffect} from 'react';

function YoutuberList() {

  return (
    <div className="App">
      <div className="container">
        {data.map(youtuber => (
          <div className="y-box" key={youtuber.id}>
            <div className="y-box-header">{youtuber.title}</div>
            <div className="y-box-body">{youtuber.description}</div>
            <div className="y-box-footer">
              <a>
                <span className='y-box-name'>by {youtuber.author}</span>
              </a>
              <span className='y-box-like'>
                  ♥ 33
              </span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default YoutuberList;
