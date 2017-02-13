import React from 'react';
import { Router, Route, IndexRoute, Link } from 'dva/router';

import Index from './routes/Index';

import Navigation from './routes/Navigation';
import PicturePage from './routes/Picture';
import JokePage from './routes/Joke';
import ArticlePage from './routes/Article';

import PictureList from './routes/picture/PictureList';
import Picture from './routes/picture/Picture';
import JokeList from './routes/joke/JokeList';
import Joke from './routes/joke/Joke';
import ArticleList from './routes/article/ArticleList';
import Article from './routes/article/Article';

export default function({ history }) {
  return (
    <Router history={history}>
      <Route path="/">
        <IndexRoute component={Index}/>
        <Route component={Navigation}>
          <Route path="picture" component={PicturePage}>
            <IndexRoute component={PictureList}/>
            <Route path="list" component={PictureList}/>
            <Route path=":id" component={Picture}/>
          </Route>
          <Route path="joke" component={JokePage}>
            <IndexRoute component={JokeList}/>
            <Route path="list" component={JokeList}/>
            <Route path=":id" component={Joke}/>
          </Route>
          <Route path="article" component={ArticlePage}>
            <IndexRoute component={ArticleList}/>
            <Route path="list" component={ArticleList}/>
            <Route path=":id" component={Article}/>
          </Route>
        </Route>
      </Route>
    </Router>
  );
};
