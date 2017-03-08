import dva from 'dva';
import './index.css';

// 1. Initialize
const app = dva();

// 2. Plugins
// app.use({});

// 3. Model
app.model(require('./models/user'));
app.model(require('./models/api'));
app.model(require('./models/apis'));
app.model(require('./models/apiInputs'));
app.model(require('./models/apiOutputs'));

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#root');
