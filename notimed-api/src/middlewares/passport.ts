import passport from "passport";
import { Strategy, ExtractJwt, StrategyOptions } from "passport-jwt";
import configEnv from "src/config/config";
import User from '../models/user.model';

const opts: StrategyOptions = {
    jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    secretOrKey: configEnv.secret_key
};

passport.use(
    new Strategy(opts,async (payload:any, done: any) => {   
        try {
            const user = await User.findById({_id: payload.id})
            
            return done(null, user)
        } catch (error) {
            done(error)
        }
    })
)