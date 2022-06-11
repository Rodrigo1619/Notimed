import User from '@models/user.model';
import bcrypt from 'bcrypt';


const isValidPassword = async function (password:string) {
    const user = new User;
    const compare = await bcrypt.compare(password, user.password);
    return compare;
}

export default isValidPassword;