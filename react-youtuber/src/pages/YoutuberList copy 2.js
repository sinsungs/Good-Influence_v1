import logo from '../logo.svg';
import '../App.css';
import $ from "jquery";
import axios from 'axios';

import React, {useState, useEffect} from 'react';

function YoutuberList() {
  const [youtubers, setYoutubers] = useState([]);

  useEffect(() => {
    // Fetch data from the server using an API call or any other method
    // Replace the URL with the actual endpoint to retrieve the data
    fetch('https://example.com/api/youtubers')
      .then(response => response.json())
      .then(data => setYoutubers(data))
      .catch(error => console.error(error));
  }, []);

  return (
    <div className="App">
      <div className="container">
        {youtubers.map(youtuber => (
          <div className="box" key={youtuber.id}>
            <div className="box-header">{youtuber.title}</div>
            <div className="box-body">{youtuber.description}</div>
            <div className="box-footer">by {youtuber.author}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default YoutuberList;
