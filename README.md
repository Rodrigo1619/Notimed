# Proyecto NotiMed

## Estandares para ramas y commits

Nos basamos en el flujo de trabajo según [git-flow](https://danielkummer.github.io/git-flow-cheatsheet/), por su facilidad en desarrollo y manejo por ramas

**Prefijos de ramas y respectivos escenarios**
- Feature, para agregar una funcionalidad: **feature/**
- Bugfix, reparación de errores en desarrollo: **bugfix/**
- Hotfix, reparación de errores en producción, ya sean generados o encontrados en esta: **hotfix/**

Con dichos prefijos, el estandar para ramas pasa a ser el siguiente:

`(feature|bugfix|hotfix)/ISSUE-CODE-<descripción-breve>`

Asi mismo con las ramas se tiene estandar para los commits en el repositorio. Los cuales tendran el siguiente estandar:

`(feat|fix|docs|refactor|chore|style): <descripción-breve>`

**Tipos de commits:**
- `feat`: Agregar una caracteristica.
- `fix`: Solucionar un bug.
- `docs`: Cambios en la documentación.
- `refactor`: Refactorización del código como cambios de nombre de variables o funciones.
- `chore`: Actualizar sin afectar el uso (actualización de paquetes, cambio de mensajes en strings, etc)
- `style`: Cambio en el formato del código (saltos de linea, indentacion, etc. que no implique cambio en funcionalidad).


## Flujo

Con lo anteriormente establecido, el flujo de trabajo a seguir en **Proyecto NotiMed** es el siguiente:

1. Revisar tarea/ticket y moverlo en [notimed-board](https://github.com/UCASV/proyecto-notimed/projects/1) a **In Progress**
2. Crear rama según estandares basados en git-flow.
3. Realizar commits según estandares.
4. Al finalizar la tarea, nuevamente la tarea/ticket debe moverse en [notimed-board](https://github.com/UCASV/proyecto-notimed/projects/1) a **Ready for QA**
5. Crear un Pull Request y solicitar review a un integrante del equipo. 
    - Si son necesarios cambios posterior a la revisión y se han realizado nuevos commits, unicamente realizar push a la rama correspondiente
6. Al finalizar la revisión (y sea correcta), el reviewer debe realizar merge a la rama `develop`.

