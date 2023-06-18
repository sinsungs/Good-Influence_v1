
// import PostList from './PostList';
// import PostDetail from './PostDetail';
// import NotFound from './NotFound';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import YoutuberCreate from './pages/YoutuberCreate';

function App() {
  return (
    <Router>
      <div>
        <Header />
        {/* <Routes> */}
          {/* <Route exact path="/" component={PostList} />
          <Route path="/post/:id" component={PostDetail} />
          <Route component={NotFound} /> */}
        {/* </Routes> */}

          <YoutuberCreate/>
        {/* <Youtuber/> */}
        <Footer />
        
      </div>
    </Router>
  );
}

export default App;