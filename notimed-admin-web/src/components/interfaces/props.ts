export interface PropVector {
    className?: string
}

export interface NavbarProps {
    title: string,
    logo: any,
    isEnabled: boolean,
    isBack: boolean
}

export interface InputProps extends PropVector {
    placeholder: string,
    type: string,
    minLength?: number,
    maxLength?: number,
    required: boolean, 
    label: string,
    icon: any,
    identifier: string
}

export interface TopVector extends PropVector {
    title: string,
    icon: any
}

export interface ButtonProps extends PropVector {
    text: string
}
