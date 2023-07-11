
// import PostList from './PostList';
// import PostDetail from './PostDetail';
// import NotFound from './NotFound';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import React, {useState, useEffect} from 'react';
import axios from 'axios';

import Header from './components/Header';
import Footer from './components/Footer';
import YoutuberCreate from './pages/YoutuberCreate';
import YoutuberList from './pages/YoutuberList';
import YoutuberRead from './pages/YoutuberRead';
import Youtuber from './pages/Youtuber';
import YoutuberPost from './pages/YoutuberPost';

function App() {

  const [message, setMessage] = useState([]);
  
  const Proxytest = () => {
    axios.get('/hello')
      .then((res) => {
        setMessage(res.data);
        alert(res.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  };

  const handleButtonClick = () => {
    Proxytest();
  };

  return (
    
    <Router>
      <div>
        <button onClick={handleButtonClick}>Proxy 테스트</button>
      </div>

        <Header />


          {/* <YoutuberList/> */}

        <Routes>
          <Route path="/youtuber" element={<Youtuber/>}/>
          <Route path="/list" element={<YoutuberList/>}/>
          <Route path="/post" element={<YoutuberPost/>}/>
          {/* <Route exact path="/" component={YoutuberList} /> */}
          <Route exact path="/YoutuberRead" element={<YoutuberRead/>} /> // Define the YoutuberRead route
        </Routes>

        <Footer />
        

    </Router>
  );
}

export default App;