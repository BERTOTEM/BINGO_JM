/**funcion de modulo express para definir la URL*/
const { Router } = require('express')
const router = Router();

const { renderIndex, renderAbout } = require('../controllers/index.controller')

/**cuando soliciten la ruta inicial renderiza el archivo index */
router.get('/', renderIndex);
router.get('/about', renderAbout);

module.exports = router;