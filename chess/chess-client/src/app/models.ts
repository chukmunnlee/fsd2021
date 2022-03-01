export const BTN_CREATE_GAME = 'create-game'
export const BTN_JOIN_GAME = 'join-game'
export const BTN_REFRESH_GAME_IDS = 'refresh-game-ids'

export const CMD_NEW = 'new'
export const CMD_JOIN = 'join'
export const CMD_START = 'start'
export const CMD_MOVE = 'move'

export interface ControlAction {
	command: string 
	gameId: string
}

export interface ChessMessage {
	command: string 
	gameId: string
	player: string
	src?: string
	dest?: string
}

export interface ChessEvent {
	src: string
	dest: string 
	player: string
}
