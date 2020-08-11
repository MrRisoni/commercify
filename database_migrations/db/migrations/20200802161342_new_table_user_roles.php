<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableUserRoles extends AbstractMigration
{

    public function change(): void
    {
        $usersRoles = $this->table('user_roles', ['signed' => false]);
        $usersRoles->addColumn('title', 'string', ['limit' => 55])
            ->addIndex(['title'], ['unique' => true])
            ->create();
    }
}
