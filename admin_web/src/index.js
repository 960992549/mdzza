import dva from 'dva';
import './index.css';

// 1. Initialize
const app = dva();

// 2. Plugins
// app.use({});

// 3. Model
app.model(require('./models/user'));
app.model(require('./models/api/api'));
app.model(require('./models/api/apis'));
app.model(require('./models/api/apiInputs'));
app.model(require('./models/api/apiOutputs'));
app.model(require('./models/api/apiInputValidators'));
app.model(require('./models/api/apiOutputFormats'));
app.model(require('./models/sys/sysRoles'));
app.model(require('./models/sys/sysRole'));
app.model(require('./models/sys/sysRolePermission'));

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#root');
