
/**me permite tener variables de entorno par iniciar variables */
require('dotenv').config();


const app = require('./server');
require('./database');

app.listen(app.get('port'), () => {
    console.log('server on por', app.get('port'))
})