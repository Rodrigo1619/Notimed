const passport = require("passport");
const { Strategy } = require("passport-local");
import User from '@models/user.model';
import isValidPassword from 'src/middlewares/valid-password';



passportort.use('signup', new Strategy({
    usernameField: 'email',
    passwordField: 'password'
}, async (email, password, done) => {
    try {
        const user = await User.create({email, password});
        return done(null, user);
    } catch (error) {
        done(error);
    }
}))

passport.use('login', new Strategy({
    usernameField: 'email',
    passwordField: 'password'
}, async (email, password, done) => {
    try {
        const user = await User.findOne({email});

        if (!user) {
            return done(null, false, {message: 'User not found'}) 
        }

        const validate = await isValidPassword(password);

        if (!validate) {
            return done(null, false, {message: 'Wrong Password'})
            
        }
        
        return done(null, user, {message: 'Login Succesful'})

    } catch (error) {
        done(error);
    }
}))
