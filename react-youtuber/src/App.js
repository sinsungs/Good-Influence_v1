
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
import Join from './pages/Join';
import Login from './pages/Login';
import MyPage from './pages/MyPage';
import Meeting from './pages/Meeting';
import Quill from './pages/Quill';
import Banner from './components/Banner';
import Category from './components/Category';

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

        {/* <Banner /> */}
        <Category />


        <Routes>
          <Route path="/quill" element={<Quill/>}/>
          <Route path="/meeting" element={<Meeting/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
          <Route path="/join" element={<Join/>}/>
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