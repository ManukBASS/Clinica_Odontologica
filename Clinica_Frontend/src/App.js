import { React, Component } from "react";
import "./App.css";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrashAlt } from "@fortawesome/free-solid-svg-icons";
import { Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import logoClinica from "./Images/paciente-icon.png";
import addPaciente from "./Images/addPaciente-icon.png"

const ENDPOINT_GET = "http://localhost:8080/pacientes/listar"
const ENDPOINT_POST = "http://localhost:8080/pacientes/agregar"
const ENDPOINT_PATCH = "http://localhost:8080/pacientes/modificarPaciente/"
const ENDPOINT_DELETE = "http://localhost:8080/pacientes/eliminar/"

// const cors = require('cors')

class App extends Component {

  state = {
    data: [],
    modalInsertar: false,
    modalEliminar: false,
    form: {
      id: '',
      nombre: '',
      apellido: '',
      domicilio: '',
      dni: '',
      fechaAlta: '',
      tipoModal: ''
    }
  }

  peticionGet = () => {
    axios.get(ENDPOINT_GET)
      .then(response => {
        this.setState({ data: response.data })
      }).catch(err => {
        console.log(err.message)
      })
  };

  peticionPost = async () => {
    delete this.state.form.id

    await axios.post(ENDPOINT_POST, this.state.form)
      .then(response => {
        this.modalInsertar()
        this.peticionGet()
      }).catch(err => {
        console.log(err.message)
      })
  }

  peticionPut = () => {
    axios.patch(ENDPOINT_PATCH + this.state.form.id, this.state.form)
      .then(response => {
        this.modalInsertar()
        this.peticionGet()
      }).catch(err => {
        console.log(err.message)
      })
  }


  peticionDelete = () => {
    axios.delete(ENDPOINT_DELETE + this.state.form.id)
      .then(response => {
        this.setState({ modalEliminar: false })
        this.peticionGet()
      }).catch(err => {
        console.log(err.message)
      })
  }

  modalInsertar = () => {
    this.setState({ modalInsertar: !this.state.modalInsertar })
  }

  seleccionarPaciente = (paciente) => {
    this.setState({
      tipoModal: 'actualizar',
      form: {
        id: paciente.id,
        nombre: paciente.nombre,
        apellido: paciente.apellido,
        domicilio: paciente.domicilio,
        dni: paciente.dni,
        fechaAlta: paciente.fechaAlta
      }
    })
  }

  handleChange = async e => {
    e.persist()
    await this.setState({
      form: {
        ...this.state.form,
        [e.target.name]: e.target.value
      }
    })
    console.log(this.state.form)
  }

  componentDidMount() {
    this.peticionGet();
  }

  render() {
    const { form } = this.state
    return (
      <div className="App">
        <br /><br />
        <div>
          <h1>
            <img src={logoClinica} alt="" style={{ width: "50px", paddingBottom: "20px", marginRight: "10px" }}></img>
            Clínica Odontológica
            <img src={logoClinica} alt="" style={{ width: "50px", paddingBottom: "20px", marginLeft: "10px" }}></img>
          </h1>
        </div>
        {/* <button className="btn btn-success" onClick={() => { this.setState({ form: null, tipoModal: 'insertar' }); this.modalInsertar() }}>Agregar Paciente<img src={addPaciente} alt="" style={{ width: "25px", marginLeft: "10px" }}></img></button> */}
        <br /><br />
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Domicilio</th>
              <th>DNI</th>
              <th>Fecha de Alta</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {this.state.data.map(paciente => {
              return (
                <tr>
                  <td>{paciente.id}</td>
                  <td>{paciente.nombre}</td>
                  <td>{paciente.apellido}</td>
                  <td>{paciente.domicilio}</td>
                  <td>{paciente.dni}</td>
                  <td>{paciente.fechaAlta}</td>
                  <td>
                    <button className="btn btn-primary" onClick={() => { this.seleccionarPaciente(paciente); this.modalInsertar() }}><FontAwesomeIcon icon={faEdit} /></button>
                    {"   "}
                    <button className="btn btn-danger" onClick={() => { this.seleccionarPaciente(paciente); this.setState({ modalEliminar: true }) }}><FontAwesomeIcon icon={faTrashAlt} /></button>
                  </td>
                </tr>
              )
            })}
          </tbody>
        </table>
        <br /><br />
        <button className="btn btn-success" onClick={() => { this.setState({ form: null, tipoModal: 'insertar' }); this.modalInsertar() }}>Agregar Paciente<img src={addPaciente} alt="" style={{ width: "22px", marginLeft: "10px" }}></img></button>


        <Modal isOpen={this.state.modalInsertar}>
          <ModalHeader style={{ display: 'block' }}>
            <span style={{ float: 'right', cursor: 'pointer' }} onClick={() => this.modalInsertar()}>❌</span>
          </ModalHeader>

          <ModalBody>
            <div className="form-group">
              <label htmlFor="id">ID</label>
              <input className="form-control" type="text" name="id" id="id" readOnly onChange={this.handleChange} value={form ? form.id : this.state.data.length + 1} />
              <br />
              <label htmlFor="nombre">Nombre</label>
              <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.handleChange} value={form ? form.nombre : ''} />
              <br />
              <label htmlFor="apellido">Apellido</label>
              <input className="form-control" type="text" name="apellido" id="apellido" onChange={this.handleChange} value={form ? form.apellido : ''} />
              <br />
              <label htmlFor="domicilio">Domicilio</label>
              <input className="form-control" type="text" name="domicilio" id="domicilio" onChange={this.handleChange} value={form ? form.domicilio : ''} />
              <br />
              <label htmlFor="dni">DNI</label>
              <input className="form-control" type="text" name="dni" id="dni" onChange={this.handleChange} value={form ? form.dni : ''} />
              <br />
              <label htmlFor="fechaAlta">Feha de Alta</label>
              <input className="form-control" type="date" name="fechaAlta" id="fechaAlta" onChange={this.handleChange} value={form ? form.fechaAlta : ''} />
            </div>
          </ModalBody>

          <ModalFooter>
            {this.state.tipoModal === 'insertar' ?
              <button className="btn btn-success" onClick={() => this.peticionPost()}>
                Insertar
              </button> : <button className="btn btn-primary" onClick={() => this.peticionPut()}>Actualizar</button>}
            <button className="btn btn-danger" onClick={() => this.modalInsertar()}>Cancelar</button>
          </ModalFooter>
        </Modal>

        <Modal isOpen={this.state.modalEliminar}>
          <ModalBody>
            Está seguro que desea eliminar el paciente {form && form.nombre} ?
          </ModalBody>
          <ModalFooter>
            <button className="btn btn-danger" onClick={() => this.peticionDelete()}>Si</button>
            <button className="btn btn-secundary" onClick={() => this.setState({ modalEliminar: false })}>No</button>
          </ModalFooter>
        </Modal>

      </div>
    )
  }
}

export default App;
