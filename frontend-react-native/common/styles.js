import { StyleSheet } from 'react-native';

export const colors = {
    bgColor: '#EAEAEA',
    primary: '#007bff',
    secondary: '#B2B2B2',
};

export default StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: colors.bgColor,
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 95
    },
    verticalSpace: {
        height: 10,
    },
    primaryButtonWide: {
        marginTop: 10,
        paddingTop: 10,
        paddingBottom: 10,
        backgroundColor: colors.primary,
        borderRadius: 10,
        borderWidth: 1,
        borderColor: '#fff',
        width: '190%',
    },
    filledButtonText: {
        color: 'white',
        textAlign: 'center'
    },
    input: {
        height: 40,
        marginBottom: 6,
        borderWidth: 1,
        padding: 10,
        width: '190%',
        borderRadius: 10,
        borderColor: '#5a5a5a'
    },
    footer: {
        height: 100
    },
    secondaryButtonWide: {
        marginTop: 10,
        paddingTop: 10,
        paddingBottom: 10,
        backgroundColor: colors.secondary,
        borderRadius: 10,
        width: '90%',
    },
    row: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-between'
    }
});