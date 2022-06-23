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
    minLenght?: number,
    maxLenght?: number,
    required: boolean, 
    label: string,
    icon: any,
    identifier: string
}

export interface ButtonProps extends PropVector {
    text: string
}
