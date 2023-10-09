import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import React, {useState, useEffect} from 'react';
import axios from 'axios';

import Header from './components/Header';
import Footer from './components/Footer';
import Banner from './components/Banner';
import Category from './components/Category';

import Meet from './pages/Meeting';
import Influencer from './pages/Influencer';
import RecommendPost from './pages/RecommendPost';
import RecommendList from './pages/RecommendList';
import Ranking from './pages/Ranking';

import Join from './pages/Join';
import Login from './pages/Login';
import MyPage from './pages/MyPage';
import KakaoLoginHandeler from './pages/KakaoLoginHandeler'

import Quill from './pages/Quill';
import Admin from './pages/Admin'


function App() {

  const [message, setMessage] = useState([]);
  
  const Proxytest = () => {
    axios.get('/api/hello')
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
        {/* <button onClick={handleButtonClick}>Proxy 테스트</button> */}
      </div>

        <Header />

        {/* <Banner /> */}
        <Category />


        <Routes>

          <Route path="/" element={<Meet/>}/>
          <Route path="/meet" element={<Meet/>}/>
          <Route path="/Influencer" element={<Influencer/>}/>
          <Route path="/post" element={<RecommendPost/>}/>
          <Route path="/list" element={<RecommendList/>}/>
          <Route path="/rank" element={<Ranking/>}/>
          
          <Route path="/quill" element={<Quill/>}/>

          <Route path="/login" element={<Login/>}/>
          <Route path="/mypage" element={<MyPage/>}/>
          <Route path="/join" element={<Join/>}/>

          <Route path="/admin" element={<Admin/>}/>

          <Route path="/login/oauth2/callback/kakao" element={<KakaoLoginHandeler />} />

        </Routes>

        <Footer />
        

    </Router>
  );
}

export default App;