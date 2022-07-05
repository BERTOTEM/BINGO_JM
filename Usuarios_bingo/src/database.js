const mongoose = require('mongoose')
const MONGODB_URI = process.env.MONGODB_URI;

//**conexion a mongo db */
mongoose.connect(MONGODB_URI, {
    useUnifiedTopology: true,
    useNewUrlParser: true

})
    .then(db => console.log('conexion exitosa'))
    .catch(err => console.log(err));
