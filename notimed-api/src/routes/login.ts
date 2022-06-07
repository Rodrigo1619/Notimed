import express from 'express';
import passport from 'passport';
import path from 'path';

require('../auth/google-auth');

const loginRouter = express();

const viewsDir = path.join(__dirname, '../views');

loginRouter.set('views', viewsDir);

loginRouter.get("/auth", (req, res)=>{
    //Va a mostrar la página de login
    res.sendFile('login.html', {root: viewsDir});
})



loginRouter.get(
    "/auth/google",
    passport.authenticate("google", {
      scope: ["email", "profile"],
    })
);

loginRouter.get("/auth/google/callback", passport.authenticate('google', {failureRedirect : '/login'}), (req, res) =>{
    res.redirect("/");

});


//loginRouter.get("/profile", )

module.exports = loginRouter;