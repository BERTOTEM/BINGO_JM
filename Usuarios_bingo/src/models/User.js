
const { Schema, model } = require('mongoose');
const bcrypt = require('bcryptjs');

const UserSchema = new Schema(
  {
    name: { type: String, trim: true },
    email: { type: String, required: true, unique: true, trim: true },
    password: { type: String, required: true },
  },
  {
    timestamps: true,
  }
);

/**
 * se usa pra cifrar la contraseña del usuario 
 * @param {*} password 
 * @returns devuelve la contraseña cifrada
 */
UserSchema.methods.encryptPassword = async (password) => {
  const salt = await bcrypt.genSalt(10);
  return await bcrypt.hash(password, salt);
};
/**
 * se usa para comparar la contraseña del de login con la  cifrada en el servidor 
 * @param {*} password 
 * @returns si es igual o no la contraseña
 */
UserSchema.methods.matchPassword = async function (password) {
  return await bcrypt.compare(password, this.password);
};

module.exports = model("User", UserSchema);
