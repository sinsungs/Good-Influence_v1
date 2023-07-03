
// import PostList from './PostList';
// import PostDetail from './PostDetail';
// import NotFound from './NotFound';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import YoutuberCreate from './pages/YoutuberCreate';
import YoutuberList from './pages/YoutuberList';
import YoutuberRead from './pages/YoutuberRead';
import Youtuber from './pages/Youtuber';

function App() {
  return (
    <Router>

        <Header />


          {/* <YoutuberList/> */}

        <Routes>
          <Route path="/youtuber" element={<Youtuber/>}/>
          <Route path="/list" element={<YoutuberList/>}/>
          {/* <Route exact path="/" component={YoutuberList} /> */}
          <Route exact path="/YoutuberRead" element={<YoutuberRead/>} /> // Define the YoutuberRead route
        </Routes>

        <Footer />
        

    </Router>
  );
}

export default App;