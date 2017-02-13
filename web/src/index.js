import './index.html';
import './index.css';
import dva from 'dva';

// 1. Initialize
const app = dva();
// const app = dva({
//     initialState: {
//       products: [
//         {name: 'dva', id: 1},
//         {name: 'antd', id: 2},
//       ],
//     },
//   });

// 2. Plugins
//app.use({});

// 3. Model
//app.model(require('./models/example'));
app.model(require('./models/picture/PictureList.js'));
app.model(require('./models/picture/Picture.js'));
app.model(require('./models/joke/JokeList.js'));
app.model(require('./models/joke/Joke.js'));
app.model(require('./models/article/ArticleList.js'));
app.model(require('./models/article/Article.js'));

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#root');
