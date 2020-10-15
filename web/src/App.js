import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { Home, Pokemon } from './pages';
import './styles/App.scss';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact={true} component={Home} />
        <Route path="pokemon/:number" component={Pokemon} />
        <Route path="*" component={Pokemon} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
