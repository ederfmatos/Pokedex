import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Header from './components/Header';
import Home from './home/Home';
import './App.scss';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Header />
        <main className="AppBody">
          <Switch>
            <Route path="/">
              <Home />
            </Route>
          </Switch>
        </main>
      </BrowserRouter>
    </div>
  );
}

export default App;
