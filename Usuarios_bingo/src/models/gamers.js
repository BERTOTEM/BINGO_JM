
const { Schema, model } = require('mongoose');


const GamersSchema = new Schema(
  {
    name: { type: String, required: true, unique: true, trim: true },
    email: { type: String, required: true, unique: true, trim: true }
  },
  {
    timestamps: true,
  }
);



module.exports = model("gamers", GamersSchema);
