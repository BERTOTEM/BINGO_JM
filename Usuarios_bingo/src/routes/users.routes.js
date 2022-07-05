/**funcion de modulo express para definir la URL*/
const { Router } = require('express')
const router = Router();

const { renderSignUpForm, signup, renderSigninForm, signin, logout, get, signin2, logout2 } = require('../controllers/users.controller')


// Routes
router.get("/users/signup", renderSignUpForm);
router.post("/users/signup", signup);
router.get("/users/signin", renderSigninForm);
router.post("/users/signin", signin2, signin);
router.post("/about", logout2, logout);
router.get("/get", get);



module.exports = router;