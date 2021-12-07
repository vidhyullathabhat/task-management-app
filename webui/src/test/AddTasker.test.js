import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import AddTasker from "../components/AddTasker";

configure({adapter: new Adapter()});

describe("Add Tasker ", () => {
    it("should render new button", () => {
        const wrapper = shallow(<AddTasker/>);
        const buttonElement = wrapper.find('#newBtn');
        expect(buttonElement).toHaveLength(1);
    });
    it('displays form when clicked', () => {
        const wrapper = shallow(<AddTasker/>);
        const buttonElement = wrapper.find('#newBtn');
        buttonElement.simulate('click');
        expect(wrapper.state('showForm')).toBe(true);
        const newForm = wrapper.find('#newForm');
        expect(newForm).toHaveLength(1);
    });
    it('displays tasks when submitted', () => {
        const wrapper = shallow(<AddTasker/>);
        const buttonElement = wrapper.find('#newBtn');
        buttonElement.simulate('click');
        const newForm = wrapper.find('#newForm');
        expect(newForm).toHaveLength(1);
        const submitBtn = wrapper.find('#newSubmitBtn');
        expect(submitBtn).toHaveLength(1);
        submitBtn.simulate('click');
        wrapper.find('#newSubmitBtn').simulate('submit', {
            target: {name: 'name', value: 'admin'} && {
                name: 'date',
                value: '2021-05-05'
            }
        });
        const taskerList = wrapper.find('#taskerList');
        expect(taskerList).toHaveLength(1);
    });
    it('displays edit and delete buttons when radio button clicked', () => {
        const wrapper = shallow(<AddTasker/>);
        const item = {
            name: 'Profit',
            date: '2021-05-05'
        }
        wrapper.setState({selectedItem: item}, () => {
            const showEditBtn = wrapper.find('#showEditBtn');
            expect(showEditBtn).toHaveLength(1);
            const showDeleteBtn = wrapper.find('#showDeleteBtn');
            expect(showDeleteBtn).toHaveLength(1);
        });
    });

    it('displays edit form when edit button is clicked', () => {
        const wrapper = shallow(<AddTasker/>);
        wrapper.setState({showEditForm: true}, () => {
            const showEditForm = wrapper.find('#editForm');
            expect(showEditForm).toHaveLength(1);
        });
    });

    it('displays tasks when edit form is submitted', () => {
        const wrapper = shallow(<AddTasker/>);
        wrapper.setState({showEditForm: true}, () => {
            wrapper.find('#editForm').simulate('submit', {
                target: {
                    name: 'date',
                    value: '2021-10-05'
                }
            });
            const taskerList = wrapper.find('#taskerList');
            expect(taskerList).toHaveLength(1);
            expect(wrapper.state('showForm')).toBe(false);
        });
    });
});