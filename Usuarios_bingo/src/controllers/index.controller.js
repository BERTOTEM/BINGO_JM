/** se crea uu un objeto*/
const indexCtrl = {};
/**funcion para renderizar index */
indexCtrl.renderIndex = (req, res) => {
    res.render('index')
};
/**funcion para renderizar about */
indexCtrl.renderAbout = (req, res) => {
    res.render('about')
};

module.exports = indexCtrl;