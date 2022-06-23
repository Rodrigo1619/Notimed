import React from "react";
import { FC } from "react";
import { PropVector } from "../interfaces/props";

const UserNotimed: FC<PropVector> = ({ className }) => {
    return (
        <svg
            className={ className }
            width="161" height="225" viewBox="0 0 161 225" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M156.974 16.0573C159.198 16.0573 161 17.8541 161 20.0729V220.818C161 223.031 159.198 224.828 156.974 224.828H4.02604C1.80208 224.828 0 223.031 0 220.818V20.0729C0 17.8541 1.80208 16.0573 4.02604 16.0573H64.401V24.0885H8.04688V216.802H152.948V24.0885H96.599V16.0573H156.974Z" fill="#45464F" />
            <path d="M140.875 32.1198C143.094 32.1198 144.901 33.9166 144.901 36.1302V204.755C144.901 206.974 143.094 208.771 140.875 208.771H20.1252C17.9012 208.771 16.0991 206.974 16.0991 204.755V36.1302C16.0991 33.9166 17.9012 32.1198 20.1252 32.1198H56.3491V40.1458H24.1512V200.74H136.849V40.1458H104.651V32.1198H140.875Z" fill="#45464F" />
            <path fillRule="evenodd" clipRule="evenodd" d="M88.5467 24.0885C88.5467 28.5208 84.9425 32.1198 80.4998 32.1198C76.0519 32.1198 72.4478 28.5208 72.4478 24.0885C72.4478 19.6562 76.0519 16.0573 80.4998 16.0573C84.9425 16.0573 88.5467 19.6562 88.5467 24.0885Z" fill="#45464F" />
            <path d="M56.349 32.0781V24.0885C56.349 10.7865 67.1667 0 80.5 0C93.8333 0 104.651 10.7865 104.651 24.0885V32.0781H108.672C110.896 32.0781 112.698 33.875 112.698 36.0885V52.1927C112.698 54.4062 110.896 56.2083 108.672 56.2083H52.3229C50.099 56.2083 48.2969 54.4062 48.2969 52.1927V36.0885C48.2969 33.875 50.099 32.0781 52.3229 32.0781H56.349ZM64.401 36.0885C64.401 38.3073 62.5938 40.1042 60.375 40.1042H56.349V48.1771H104.651V40.1042H100.625C98.401 40.1042 96.599 38.3073 96.599 36.0885V24.0885C96.599 15.224 89.3854 8.02604 80.5 8.02604C71.6094 8.02604 64.401 15.224 64.401 24.0885V36.0885Z" fill="#45464F" />
            <path d="M81.0001 120C90.2084 120 97.6668 112.542 97.6668 103.333C97.6668 94.125 90.2084 86.6666 81.0001 86.6666C71.7917 86.6666 64.3334 94.125 64.3334 103.333C64.3334 112.542 71.7917 120 81.0001 120ZM81.0001 128.333C69.8751 128.333 47.6667 133.917 47.6667 145V149.167C47.6667 151.458 49.5417 153.333 51.8334 153.333H110.167C112.458 153.333 114.333 151.458 114.333 149.167V145C114.333 133.917 92.1251 128.333 81.0001 128.333Z" fill="#45464F" />
        </svg>

    );
};

export default UserNotimed;