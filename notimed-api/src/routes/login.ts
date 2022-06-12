
import express from 'express';
import { login, register } from 'src/controllers/user-controller';


const loginRouter = express();

loginRouter.post("/signup",  register);

loginRouter.post("/signin", login);

module.exports = loginRouter;






































/* const viewsDir = path.join(__dirname, '../views');

loginRouter.set('views', viewsDir);  */
/* 
loginRouter.get("/signin", (req, res)=>{
    //Va a mostrar la página de login
    res.sendFile('login.html', {root: viewsDir});
}) 

loginRouter.get("/signup", (req, res)=>{
    res.sendFile('signup.html', {root: viewsDir});
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
loginRouter.post('/signup', passport.authenticate('signup',{session: false}), async (req, res, next) => {
    res.json({
        message: 'Signup Succesful',
        user: req.user
    })
})

loginRouter.post('/signin',async (req, res, next) => {
    passport.authenticate('login',async (err, user, info) => {
        try {
            if(err || user){
              const error = new Error('New error')
              return next(error);  
            }

            req.login(user, {session: false},async (err) => {
                if(err) return next(err)
                const body = {_id: user._id, lastName: user.lastName, email: user.email, birthday: user.birthday, gender: user.gender}
                const token = jwt.sign({user:body}, 'top_secret')
                return res.json({token})
            })
        } catch (error) {
            
        }
    }) (req, res, next)
}) */



