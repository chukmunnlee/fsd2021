import {CMD_JOIN, CMD_MOVE} from "./models"

export const mkJoinMessage = (gameId: string) => {
	return JSON.stringify({ command: CMD_JOIN, gameId })
}

export const mkMoveMessage = (gameId: string, src: string, dest: string, player: string) => {
	return JSON.stringify({
		command: CMD_MOVE, gameId,
		src, dest, player
	})
}
