import React ,{createContext,useState}from 'react'
import { View, Text } from 'react-native'
import AsyncStorage from '@react-native-async-storage/async-storage';


const AuthContext = createContext();


export function AuthProvider({ children }) {

    const getAsyncStorageItem = async (key, defaultValue) => {
        try {
          const item = await AsyncStorage.getItem(key);
          return item ? JSON.parse(item) : defaultValue;
        } catch (e) {
          console.error(`Error fetching ${key} from AsyncStorage`, e);
          return defaultValue;
        }
      };


const [jwtToken, setToken] = useState(AsyncStorage.getItem('jwtToken') || '');
  const [userId, setUserId] = useState(AsyncStorage.getItem('userId') || '');
  const [username, setUsername] = useState(AsyncStorage.getItem('username') || '');
  const [branchId, setBranchId] = useState(AsyncStorage.getItem('branchId') || '');
  const [companyId, setCompanyId] = useState(AsyncStorage.getItem('companyid') || '');
  const [role, setRole] = useState(AsyncStorage.getItem('role') || '');
  const [companyname, setCompanyname] = useState(AsyncStorage.getItem('companyname') || '');
  const [branchname, setBranchname] = useState(AsyncStorage.getItem('branchname') || '');
  const [logintype, setLogintype] = useState(AsyncStorage.getItem('logintype') || '');
  const [logintypeid, setLogintypeid] = useState(AsyncStorage.getItem('logintypeid') || '');
  const [userType, setUserType] = useState(AsyncStorage.getItem('userType') || '');
//   const [userRights, setUserRights] = useState(getSessionStorageItem('userRights', []));
//   const [parentMenus, setParentMenus] = useState(getSessionStorageItem('parentMenus', []));
//   const [childMenus, setChildMenus] = useState(getSessionStorageItem('childMenus', []));
//   const [tabMenus, setTabMenus] = useState(getSessionStorageItem('tabMenus', []));
    
const isAuthenticated = jwtToken !== '';

const login = (
    newToken,
    newUserId,
    newUsername,
    newBranchId,
    newCompanyId,
    newRole,
    newCompanyname,
    newBranchname,
    newLogintype,
    newLogintypeid,
    newUsertype,
    // newUserRights,
    // newParentMenus,
    // newChildMenus,
    // newTabMenus
)=>{
    AsyncStorage.setItem('jwtToken', newToken);
    AsyncStorage.setItem('userId', newUserId);
    AsyncStorage.setItem('username', newUsername);
    AsyncStorage.setItem('branchId', newBranchId);
    AsyncStorage.setItem('companyId', newCompanyId);
    AsyncStorage.setItem('role', newRole);
    AsyncStorage.setItem('companyname', newCompanyname);
    AsyncStorage.setItem('branchname', newBranchname);
    AsyncStorage.setItem('logintype', newLogintype);
    AsyncStorage.setItem('logintypeid', newLogintypeid);
    AsyncStorage.setItem('userType', newUsertype);
    // sessionStorage.setItem('userRights', JSON.stringify(newUserRights));
    // sessionStorage.setItem('parentMenus', JSON.stringify(newParentMenus));
    // sessionStorage.setItem('childMenus', JSON.stringify(newChildMenus));
    // sessionStorage.setItem('tabMenus', JSON.stringify(newTabMenus));



    setToken(newToken);
    setUserId(newUserId);
    setUsername(newUsername);
    setBranchId(newBranchId);
    setCompanyId(newCompanyId);
    setRole(newRole);
    setCompanyname(newCompanyname);
    setBranchname(newBranchname);
    setLogintype(newLogintype);
    setLogintypeid(newLogintypeid);
    setUserType(newUsertype);
    // setUserRights(newUserRights);
    // setParentMenus(newParentMenus);
    // setChildMenus(newChildMenus);
    // setTabMenus(newTabMenus);

};


const logout = () => {
    AsyncStorage.removeItem('jwtToken');
    AsyncStorage.removeItem('userId');
    AsyncStorage.removeItem('username');
    AsyncStorage.removeItem('branchId');
    AsyncStorage.removeItem('companyId');
    AsyncStorage.removeItem('role');
    AsyncStorage.removeItem('companyname');
    AsyncStorage.removeItem('branchname');
    AsyncStorage.removeItem('logintype');
    AsyncStorage.removeItem('logintypeid');
    AsyncStorage.removeItem('userType');
    // AsyncStorage.removeItem('userRights');
    // AsyncStorage.removeItem('parentMenus');
    // AsyncStorage.removeItem('childMenus');
    // AsyncStorage.removeItem('tabMenus');

    setToken('');
    setUserId('');
    setUsername('');
    setBranchId('');
    setCompanyId('');
    setRole('');
    setCompanyname('');
    setBranchname('');
    setLogintype('');
    setLogintypeid('');
    setUserType('');
    // setUserRights([]);
    // setParentMenus([]);
    // setChildMenus([]);
    // setTabMenus([]);
  };

  const authContextValue = {
    jwtToken,
    userId,
    username,
    branchId,
    companyId,
    role,
    companyname,
    branchname,
    logintype,
    logintypeid,
    userType,
    isAuthenticated,
    login,
    logout,
    // userRights,
    // parentMenus,
    // childMenus,
    // tabMenus,
  };
  return (
    <AuthContext.Provider value={authContextValue}>{children}</AuthContext.Provider>
  );



}

export default AuthContext;


