const { text } = require("express");
const User = require('../models/User');
const Gamers = require('../models/gamers');
const passport = require('passport');

/** se crea un un objeto*/
const usersCtrl = {};
/**funcion para renderizar users/signup el cual es un formulario pra registrarnos */
usersCtrl.renderSignUpForm = (req, res) => {
    res.render('users/signup')
};
/**
 * Funcion para guardar un usuario nuevo se toma la informacion digitada desde el formulario la cual se guarda en una const.
 * se verifica que las contraseñas tengan una longitud minima y que sean identicas en caso de qeu no se muestra un erros en frontend para avisar al usuario
 * tambien se verifica que el email qeu use no se encuentre ya registrado
 * 
 */
usersCtrl.signup = async (req, res) => {
    const errors = [];
    const { name, email, password, confirm_password } = req.body;
    if (password != confirm_password) {
        errors.push({ text: 'passwords no coninciden' });
    }
    if (password.length < 4) {
        errors.push({ text: 'passwords al menos compuestas por 4 caracters' });
    }
    if (errors.length > 0) {
        res.render('users/signup', {
            errors,
            name,
            email
        })
    } else {
        const emailUser = await User.findOne({ email: email });
        if (emailUser) {
            req.flash('error_msg', 'El correo ya esta en uso');
            res.redirect('/users/signup');
        } else {
            const newUser = new User({ name, email, password });
            newUser.password = await newUser.encryptPassword(password)
            await newUser.save();
            req.flash('success_msg', 'usuario registrado');
            res.redirect('/users/signin');
        }
    }
};
/** formulario para iniciar sesion  */
usersCtrl.renderSigninForm = (req, res) => {
    res.render('users/signin')
};
/**funcion para renderizar sigin el caul nos dice si estamos iniciando sesion exitosamente */
usersCtrl.signin = passport.authenticate('local', {
    failureRedirect: '/users/signin',
    successRedirect: 'http://localhost:4001/Lobby',//COLOCAR ACA EL BINGO URL
    failureFlash: true


});

usersCtrl.logout = (req, res) => {
    req.logout(function (err) {
        if (err) { return next(err); }
        req.flash('success_msg', 'Cerrado de sesion correcto');
        res.redirect('/users/signin');
    });

};


usersCtrl.logout2 = async (req, res, next) => {
    const { name } = req.body;
    const nameUser = await Gamers.findOne({ name: name });
    if (nameUser) {
        Gamers.deleteOne({ name: name }).then(function () {
            console.log("Data deleted"); // Success
            return next();
        }).catch(function (error) {
            console.log(error); // Failure
            res.redirect('/about');
        })
    } else {
        req.flash('error_msg', 'Ese no es el nombre con el que iniciaste sesion');
        res.redirect('/about');
    }


};
usersCtrl.get = async (req, res) => {

    const Jugadores = await Gamers.find({});
    res.json({ Jugadores });
};


usersCtrl.signin2 = async (req, res, next) => {
    const { name, email, password } = req.body;
    const user = await User.findOne({ email })
    const match = await user.matchPassword(password)
    const nameUser = await Gamers.findOne({ name: name });
    const emailUser = await Gamers.findOne({ email: email });
    if ((!emailUser) && (match) ) {
        if (nameUser) {
            req.flash('error_msg', 'El nombre  ya esta en uso en una partida');
            res.redirect('/users/signin');
        } else {
            const newgamer = new Gamers({ name, email });
            await newgamer.save();
            return next();
        }
    } else {
        req.flash('error_msg', 'el correo ya esta en uso en una partida');
        res.redirect('/users/signin');
    }

}

module.exports = usersCtrl;