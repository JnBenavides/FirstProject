public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}


Parcial Práctico — Programación Orientada a Objetos
Grupo C: Sistema Universitario
Modalidad: Parejas · Entrega: Código fuente + diagrama de clases

Contexto
Una universidad necesita modelar su comunidad académica mediante programación orientada a objetos. Estudiantes, profesores y estudiantes graduados comparten características como personas, pero tienen comportamientos muy distintos. Tu tarea es diseñar e implementar la jerarquía de clases que represente este sistema, aplicando los conceptos de herencia, sobrecarga y sobreescritura vistos en clase.

Diagrama de clases requerido
@startuml

skinparam classBackgroundColor #1e1e2e
skinparam classBorderColor #89b4fa
skinparam classArrowColor #cba6f7
skinparam classFontColor #cdd6f4
skinparam classAttributeFontColor #a6e3a1
skinparam classStereotypeFontColor #f38ba8
skinparam backgroundColor #181825

abstract class Persona {
  - nombre: String
  - identificacion: String
  - correo: String
  + Persona(nombre, identificacion, correo)
  + presentarse(): void
  + actualizarCorreo(correo: String): void
  + getNombre(): String
}

class Estudiante {
  - codigo: String
  - carrera: String
  - materiasInscritas: String[]
  - totalMaterias: int
  + Estudiante(nombre, identificacion, correo, codigo, carrera)
  + presentarse(): void
  + inscribir(materia: String): void
  + inscribir(materia: String, grupo: int): void
  + mostrarMaterias(): void
}

class EstudianteGraduado {
  - tituloObtenido: String
  - proyectoGrado: String
  - directorTesis: String
  + EstudianteGraduado(nombre, id, correo, codigo, carrera, titulo, proyecto, director)
  + presentarse(): void
  + mostrarProyecto(): void
}

class Profesor {
  - departamento: String
  - horasContrato: int
  - salarioBase: double
  + Profesor(nombre, identificacion, correo, departamento, horasContrato, salarioBase)
  + presentarse(): void
  + final calcularSalario(): double
  + mostrarInfo(): void
}

Persona <|-- Estudiante
Persona <|-- Profesor
Estudiante <|-- EstudianteGraduado

note bottom of EstudianteGraduado : <<final>>\nNo puede heredarse
note right of Profesor : calcularSalario() es final\nNo puede sobreescribirse

@enduml

Cómo generar el diagrama:

Copia el bloque entre ```plantuml y ```
Ve a plantuml.com/plantuml y pégalo
Descarga la imagen SVG o PNG
Colócala en tu proyecto con el nombre diagrama-universitario.svg
En tu .md incorpórala así: ![Diagrama de clases](diagrama-universitario.svg)
Alternativa con extensión VS Code: instala PlantUML de jebbs, escribe el bloque en un .puml y presiona Alt+D para previsualizar.

Especificación detallada de clases
Clase Persona — Superclase
Representa cualquier miembro de la comunidad universitaria. Es la base de toda la jerarquía.

Atributos:

Atributo	Tipo	Visibilidad	Descripción
nombre	String	private	Nombre completo
identificacion	String	private	Número de documento
correo	String	private	Correo institucional
Constructor requerido:

Recibe nombre, identificación y correo
Validar que ninguno de los tres sea nulo o vacío; si lo es, asignar "No definido"
Métodos requeridos:

presentarse()

PSEUDOCÓDIGO:
  Imprimir "Hola, soy " + nombre
  Imprimir "Identificación: " + identificacion
  Imprimir "Correo: " + correo

actualizarCorreo(nuevoCorreo)

PSEUDOCÓDIGO:
  SI nuevoCorreo contiene "@" Y nuevoCorreo contiene "." ENTONCES
    correo = nuevoCorreo
    Imprimir "Correo actualizado a: " + correo
  SI NO
    Imprimir "Correo inválido. No se realizó el cambio."
  FIN SI

getNombre() — retorna el nombre.

Clase Estudiante — Subclase de Persona
Representa un estudiante activo de pregrado. Puede inscribir materias con o sin grupo específico.

Atributos adicionales:

Atributo	Tipo	Visibilidad	Descripción
codigo	String	private	Código estudiantil (ej: "2024100123")
carrera	String	private	Programa académico
materiasInscritas	String[]	private	Arreglo de hasta 10 materias
totalMaterias	int	private	Contador de materias actuales, inicia en 0
Constructor requerido:

Llama al constructor de Persona usando super
Inicializa código y carrera
Crea el arreglo de materias con capacidad para 10
totalMaterias inicia en 0
Métodos requeridos:

presentarse() (SOBREESCRITURA)

PSEUDOCÓDIGO:
  Llamar super.presentarse()
  Imprimir "Soy estudiante de: " + carrera
  Imprimir "Código estudiantil: " + codigo
  Imprimir "Materias inscritas: " + totalMaterias

inscribir(materia) (primera versión)

PSEUDOCÓDIGO:
  SI totalMaterias >= 10 ENTONCES
    Imprimir "Límite de materias alcanzado (máximo 10)"
    RETORNAR
  FIN SI
  materiasInscritas[totalMaterias] = materia
  totalMaterias = totalMaterias + 1
  Imprimir "Materia inscrita: " + materia + " (sin grupo específico)"

inscribir(materia, grupo) (segunda versión — SOBRECARGA)

PSEUDOCÓDIGO:
  SI totalMaterias >= 10 ENTONCES
    Imprimir "Límite de materias alcanzado (máximo 10)"
    RETORNAR
  FIN SI
  registro = materia + " - Grupo " + grupo
  materiasInscritas[totalMaterias] = registro
  totalMaterias = totalMaterias + 1
  Imprimir "Materia inscrita: " + registro

Estas dos versiones de inscribir constituyen la sobrecarga: mismo nombre, diferente lista de parámetros.

mostrarMaterias()

PSEUDOCÓDIGO:
  SI totalMaterias == 0 ENTONCES
    Imprimir "No hay materias inscritas"
    RETORNAR
  FIN SI
  Imprimir "Materias de " + nombre + ":"
  PARA i DESDE 0 HASTA totalMaterias - 1 HACER
    Imprimir "  " + (i+1) + ". " + materiasInscritas[i]
  FIN PARA

Clase EstudianteGraduado — Subclase de Estudiante · final
⚠ Esta clase debe declararse como final: ninguna otra clase podrá heredar de ella.

Un estudiante graduado ya terminó su carrera, tiene un título, un proyecto de grado y un director de tesis.

Atributos adicionales:

Atributo	Tipo	Visibilidad	Descripción
tituloObtenido	String	private	Título profesional (ej: "Ingeniero de Sistemas")
proyectoGrado	String	private	Nombre del proyecto de grado
directorTesis	String	private	Nombre del director o asesor
Constructor requerido:

Llama al constructor de Estudiante usando super
Inicializa título, proyecto y director
Métodos requeridos:

presentarse() (SOBREESCRITURA)

PSEUDOCÓDIGO:
  Llamar super.presentarse()
  Imprimir "Título obtenido: " + tituloObtenido
  Imprimir "Proyecto de grado: " + proyectoGrado
  Imprimir "Director de tesis: " + directorTesis

mostrarProyecto()

PSEUDOCÓDIGO:
  Imprimir "=== Proyecto de Grado ==="
  Imprimir "Autor: " + nombre
  Imprimir "Proyecto: " + proyectoGrado
  Imprimir "Director: " + directorTesis
  Imprimir "Título al graduarse: " + tituloObtenido

Clase Profesor — Subclase de Persona
Un profesor universitario. Su salario se calcula con una fórmula fija que ninguna subclase puede cambiar.

Atributos adicionales:

Atributo	Tipo	Visibilidad	Descripción
departamento	String	private	Departamento al que pertenece
horasContrato	int	private	Horas semanales contratadas
salarioBase	double	private	Salario base mensual en pesos
Constructor requerido:

Llama al constructor de Persona usando super
Inicializa departamento, horas de contrato y salario base
Métodos requeridos:

presentarse() (SOBREESCRITURA)

PSEUDOCÓDIGO:
  Llamar super.presentarse()
  Imprimir "Soy profesor del departamento de: " + departamento
  Imprimir "Contrato: " + horasContrato + " horas semanales"

calcularSalario() (SOBREESCRITURA + final — retorna double)

PSEUDOCÓDIGO:
  // Fórmula institucional fija: no puede ser alterada por subclases
  // Prima de 15% sobre el salario base por cada 10 horas sobre 20
  horasExtra = horasContrato - 20
  SI horasExtra < 0 ENTONCES
    horasExtra = 0
  FIN SI
  bonificacion = (horasExtra / 10) * (salarioBase * 0.15)
  salarioTotal = salarioBase + bonificacion
  Imprimir "Salario de " + nombre + ": $" + salarioTotal
  Imprimir "  Base: $" + salarioBase
  Imprimir "  Bonificación por horas: $" + bonificacion
  RETORNAR salarioTotal

mostrarInfo()

PSEUDOCÓDIGO:
  Llamar presentarse()
  Imprimir "Salario mensual: $" + calcularSalario()

Clase principal Main
Crea una clase Main con el método main que demuestre el funcionamiento del sistema:

PSEUDOCÓDIGO:
  // Crear personas de cada tipo
  Crear Estudiante con nombre "Ana Martínez", id "1001234567", correo "ana@uni.edu",
        código "2024100101", carrera "Ingeniería de Sistemas"

  Crear EstudianteGraduado con nombre "Carlos Pérez", id "1009876543", correo "carlos@uni.edu",
        código "2020100045", carrera "Ingeniería de Sistemas",
        título "Ingeniero de Sistemas",
        proyecto "Sistema de detección de fraude con ML",
        director "Dr. Ramírez"

  Crear Profesor con nombre "Dra. Laura Gómez", id "52001234", correo "lgomez@uni.edu",
        departamento "Ciencias de la Computación",
        horasContrato 32, salarioBase 4500000.0

  // Demostrar SOBRECARGA de inscribir
  Llamar estudiante.inscribir("Programación OOP")            // sin grupo
  Llamar estudiante.inscribir("Bases de Datos", 2)           // con grupo 2
  Llamar estudiante.inscribir("Estructuras de Datos", 1)     // con grupo 1
  Llamar estudiante.mostrarMaterias()

  // El graduado también puede inscribir (heredó el método)
  Llamar graduado.inscribir("Seminario de Investigación", 1)

  // Demostrar SOBREESCRITURA de presentarse en los tres niveles
  Imprimir "--- ESTUDIANTE ---"
  Llamar estudiante.presentarse()       // versión de Estudiante

  Imprimir "--- ESTUDIANTE GRADUADO ---"
  Llamar graduado.presentarse()         // versión de EstudianteGraduado (incluye titulo y proyecto)

  Imprimir "--- PROFESOR ---"
  Llamar profesor.presentarse()         // versión de Profesor

  // Demostrar calcularSalario final
  Llamar profesor.calcularSalario()

  // Actualizar correos usando el método heredado de Persona
  Llamar estudiante.actualizarCorreo("ana.martinez@universidad.edu.co")
  Llamar profesor.actualizarCorreo("correo-invalido")  // debe rechazarse

  // Mostrar proyecto del graduado
  Llamar graduado.mostrarProyecto()

  // Polimorfismo: arreglo de Persona
  Crear arreglo comunidad[] con estudiante, graduado y profesor
  Imprimir "--- TODOS SE PRESENTAN ---"
  PARA CADA persona EN comunidad HACER
    Llamar persona.presentarse()  // cada uno tiene su versión
    Imprimir "----"
  FIN PARA

Requisitos de entrega
[ ] Clase Persona correctamente definida con atributos y métodos base
[ ] Estudiante con sobreescritura de presentarse y sobrecarga de inscribir
[ ] EstudianteGraduado declarado final con sobreescritura de presentarse
[ ] Profesor con calcularSalario declarado final
[ ] Clase Main que demuestre todos los escenarios incluyendo el arreglo polimórfico
[ ] Diagrama de clases en PlantUML
Rúbrica
Criterio	Puntos
Superclase Persona correctamente definida con atributos y métodos	15
Sobrecarga de inscribir en Estudiante funcionando correctamente	10
Estudiante con sobreescritura de presentarse en tres niveles	20
EstudianteGraduado final con sobreescritura completa	20
Profesor con calcularSalario final y fórmula correcta	20
Main con escenarios completos y arreglo polimórfico	10
Diagrama de clases PlantUML correcto	5
Total	100
