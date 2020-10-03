import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import { Header } from './components';
import './styles/App.scss';

import Routes from './routes';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <main className="AppBody">
        <Routes />
      </main>
    </BrowserRouter>
  );
}

export default App;
