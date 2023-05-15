import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>

      <div className="container">
      <div className="box">첫번째 박스</div>
      <div className="box">두번째 박스</div>
      <div className="box">세번째 박스</div>
      <div className="box">네번째 박스</div>
      <div className="box">첫번째 박스</div>
      <div className="box">두번째 박스</div>
      <div className="box">세번째 박스</div>
      <div className="box">네번째 박스</div>
    </div>

    </div>
  );
}

export default App;
