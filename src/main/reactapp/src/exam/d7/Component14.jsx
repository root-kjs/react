import Button from '@mui/joy/Button'; //대문잘 되어 있는 것은 컴포넌트라 임포트를 반듯시 해야 한다. 
import Box from '@mui/joy/Box';
import Input from '@mui/joy/Input';
import Select from '@mui/joy/Select';
import Option from '@mui/joy/Option';
import Switch from '@mui/joy/Switch';
import { useState } from 'react';
import Avatar from '@mui/joy/Avatar';
import SideBar from './SideBar';

export default function Component14( props ){

    // 3. 
      const handleChange = (event, newValue) => {
        alert(`You chose "${newValue}"`);
    };
    // 4. 
    const [checked, setChecked] = useState(false);

    // 6.
    const [open, setOpen] = useState(false);
    const [open2, setOpen2] = useState(false);

//================================ JSX ===========================================
    return(<>
      <h5> MUI 설치 : npm install @mui/joy @emotion/react @emotion/styled </h5>
        <p> 1. 소문자 마크업 : html , 대문자 마크업 : 컴포넌트( 다른 패키지이면 import ) </p>
        <button> html </button>
        <Button variant="solid">Hello world</Button>
        <p> 2. 마크업 속성 props 이란 : 마크업 안에 , 마크업 속성명=속성값  </p>

        <h5> 1. 버튼 : https://mui.com/joy-ui/react-button/ </h5>
        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap' }}>
            <Button>Button</Button>
            <Button disabled>Disabled</Button>
            <Button loading>Loading</Button>
        </Box>
        
        <h5> 2. 입력상자 : https://mui.com/joy-ui/react-input/ </h5>
        <Input placeholder="Type in here…" />

        <h5> 3. 선택상자 : https://mui.com/joy-ui/react-select/ </h5>

        <Select defaultValue="dog" onChange={handleChange}>
            <Option value="dog">Dog</Option>
            <Option value="cat">Cat</Option>
            <Option value="fish">Fish</Option>
            <Option value="bird">Bird</Option>
        </Select>

        <h5> 4. 스위치on/off : https://mui.com/joy-ui/react-switch/  </h5>
        <Switch
            checked={checked}
            onChange={(event) => setChecked(event.target.checked)}
        />
        <h5> 5. 아바타 : https://mui.com/joy-ui/react-avatar/  </h5>

        {/* {} //1번째 중괄호 jsx, 2번째 중괄호 css 객체 시작 --> - 하이픈 제외하고 카멜표기법으로 사용 */}

        <Box sx={{ display: 'flex', gap: 2 }} style={{ backgroundColor:'red' }}>
            <Avatar />
            <Avatar>JG</Avatar>
            <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </Box>

        <h5> 6. 리스트 : https://mui.com/joy-ui/react-list/  </h5>
        <SideBar />


    </>)
}// fun end