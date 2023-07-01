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
          <div className="box" key={youtuber.id}>
            <div className="box-header">{youtuber.title}</div>
            <div className="box-body">{youtuber.description}</div>
            <div className="box-footer">
              <a>
                <span className='box-name'>by {youtuber.author}</span>
              </a>
              <span className='box-like'>
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
