import User from "@models/user.model";
import configEnv from "config/config";

import passport from "passport";

const GoogleStrategy = require("passport-google-oauth2").Strategy;


const passportGoogleConfig = ()=>{
    passport.use(new GoogleStrategy({
    clientID: configEnv.google_client_id,
    clientSecret: configEnv.google_client_secret,
    callbackURL: "http://localhost:3000/oauth2/auth/google/callback",
    passReqToCallback : true
    },
    async (accessToken:string, refreshToken: string, profile:any, done:any) => {
    try {
    let existingUser = await User.findOne({ 'google.id': profile.id });
    // if user exists return the user 
    if (existingUser) {
    return done(null, existingUser);
    }
    // if user does not exist create a new user 
    console.log('Creating new user...');
    const newUser = new User({
        method: 'google',
        google: {
            name: profile.displayName,
            email: profile.emails[0].value
        }
    });
        await newUser.save();
        return done(null, newUser);
    } catch (error) {
        return done(error, false)
        }
    }
    ));

    passport.serializeUser((user:any, done)=>{
        done(null, user.id);
    });

    passport.deserializeUser((id, done)=>{
        User.findById(id, function (err:any, user:any) {
            done(err, user);
        })
    }) 
}


export default passportGoogleConfig;
