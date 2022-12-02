import { TextInput } from 'react-native';

export default function SearchBar({ keyword, setKeyword }) {
    return (
        <TextInput
            placeholder='Search'
            onChangeText={setKeyword}
            value={keyword}
            style={{
                height: 40,
                backgroundColor: '#dedcdc',
                margin: 10,
                borderRadius: 8,
                paddingHorizontal: 10
            }}
        />
    );
}