import React from 'react';
import {Button, Col, FormGroup, Input} from "reactstrap";
import DatePicker from "react-datepicker";

// import required css from library
import "react-datepicker/dist/react-datepicker.css";
import {createNewTasker, deleteTasker, editTasker, getAllTaskers} from "./apiActions";


class AddTasker extends React.Component {

     emptyItem = {
        name: '',
        date: ''
    }
    state = {
        name: "",
        date: new Date(),
        showForm: false,
        taskers: [],
        selectedItem: this.emptyItem,
        showEditForm: false,
    };

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
    }


    async handleSubmit(e) {
        e.preventDefault();
        if (this.state.name === "" || this.state.date === "") {
            alert("All fields are mandatory!");
            return;
        }
        await createNewTasker(this.state);
        const response = await getAllTaskers();
        this.setState({taskers: response});
        this.refreshState();
    };

    async handleEdit(e) {
        e.preventDefault();
        if(this.state.selectedItem === this.emptyItem) {
            alert("Select one entry to edit!");
            return;
        }
        var item = {
            name: this.state.selectedItem.name,
            date: this.state.date
        };
        await editTasker(this.state.selectedItem.name, item);
        const response = await getAllTaskers();
        this.setState({taskers: response});
        this.refreshState();
    };

    refreshState() {
        this.setState({showForm: false});
        this.setState({showEditForm: false});
        this.setState({name:'', date:''});
        this.setState({selectedItem: this.emptyItem})
    }

    async handleDelete(e) {
        e.preventDefault();
        if(this.state.selectedItem === this.emptyItem) {
            alert("Select one entry to delete!");
            return;
        }
        await deleteTasker(this.state.selectedItem.name)
        const response = await getAllTaskers();
        this.setState({taskers: response});
        this.refreshState();
    };

    render() {

        const renderResponseList = this.state.taskers.map(tasker => {
            return (
                <div className="item"
                     style={{border: '1px solid black', backgroundColor: 'lightgrey', width: '30%', height: '40%'}}>
                    <div className="content">
                        <div className="header">{tasker.name}</div>
                        <div>{tasker.date}</div>
                        <div style={{display: "flex", justifyContent: 'flex-end'}}>
                            <Input type="radio" name="radio"
                                   onClick={(e) => this.setState({selectedItem: tasker})}
                            /></div>
                    </div>
                </div>
            );
        });

        return (
            <div id="container">
                <form className="button form" style={{display: "flex", justifyContent: 'flex-end'}}>
                    <Button id = "newBtn" className="ui button blue" onClick={(e) => this.setState({showForm: true})}>NEW</Button>
                </form>
                <div>
                    {this.state.showForm && (
                        <div>
                            <form id="newForm" onSubmit={this.handleSubmit}>
                                <Col>
                                    <FormGroup row>
                                        <label>Name</label>
                                        <Input type="text" id='name' name="name" placeholder="Name"
                                               onChange={(e) => this.setState({name: e.target.value})}/>
                                    </FormGroup>
                                </Col>
                                <Col>
                                    <FormGroup row>
                                        <label>Date</label>
                                        <DatePicker name="date" id='date' showTimeSelect dateFormat="yyyy-MM-dd HH:mm" placeholderText="Select a Date"
                                                        selected={this.state.date}
                                                        onChange={(date) => this.setState({date: date})}/>
                                    </FormGroup>
                                </Col>
                                <Button id='newSubmitBtn' type="submit" color="primary">SUBMIT</Button>

                            </form>
                        </div>)}
                </div>
                <div id='taskerList' className="ui celled list">{renderResponseList}</div>
                <div>
                    {this.state.selectedItem !== this.emptyItem && (
                        <div>
                            <Button id='showEditBtn' type="submit" color="primary"
                                    onClick={(e) => this.setState({showEditForm: true})}>EDIT</Button>
                            <Button id='showDeleteBtn' type="submit" color="primary" onClick={(e) => this.handleDelete(e)}>DELETE</Button>

                        </div>

                    )}
                </div>
                <div>
                    {this.state.showEditForm && (
                        <div>
                            <form id='editForm' onSubmit={this.handleEdit}>
                                <Col>
                                    <FormGroup row>
                                        <label>Date</label>
                                        <DatePicker name="date"  showTimeSelect dateFormat="yyyy-MM-dd HH:mm" placeholderText="Select a Date"
                                                       selected={this.state.date}
                                                        onChange={(date) => this.setState({date: date})}/>
                                    </FormGroup>
                                </Col>
                                <Button id='editSubmit' type="submit" color="primary">EDIT</Button>
                            </form>
                        </div>

                    )}
                </div>
            </div>
        );
    }
}

export default AddTasker;