const express = require('express');
const exphbs = require('express-handlebars');
const path = require('path');
const flash = require('connect-flash');
const session = require('express-session');
const passport = require('passport');


//Initializations
const app = express();
require('./config/passport');


//SETTINGS
/*** establece port primero lo busca en variales de entorno si no encuentra usa el 40000*/
app.set('port', process.env.PORT || 4000)
/** establece donde esta la carpeta de views
 *  __dirname  busca desde la raiz la ruta de la carpeta
 */
app.set('views', path.join(__dirname, 'views'));
/** configuracion de motor de plantillas*/
app.engine('.hbs', exphbs.engine({
    defaultLayout: 'main',
    layoutsDir: path.join(app.get('views'), 'layouts'),
    partialsDir: path.join(app.get('views'), 'partials'),
    extname: '.hbs'
}));
app.set('view engine', '.hbs');




//middlewarns
/*** convertirlo en un objeto json*/
app.use(express.urlencoded({ extended: false }));
app.use(session({
    secret: 'secret',
    resave: true,
    saveUninitialized: true
}));
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());



//Global variable
app.use((req, res, next) => {
    res.locals.success_msg = req.flash("success_msg");
    res.locals.error_msg = req.flash("error_msg");
    res.locals.error = req.flash("error");
    res.locals.user = req.user || null;
    next();
});

//Routes
app.use(require('./routes/index.routes'));
app.use(require('./routes/users.routes'));


//static files
//**le decimo al programa donde esta la carpeta publica por medio de una funcion de express */
app.use(express.static(path.join(__dirname, 'public')));



module.exports = app;
