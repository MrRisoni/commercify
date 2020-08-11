<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableUsers extends AbstractMigration
{

    public function change(): void
    {

        // for bigint search MySQLAdapter
        $users = $this->table('users', ['signed' => false]);
        $users->addColumn('username', 'string', ['limit' => 20])
              ->addColumn('password', 'string', ['limit' => 40])
              ->addColumn('password_salt', 'string', ['limit' => 40])
              ->addColumn('email', 'string', ['limit' => 100])
              ->addColumn('first_name', 'string', ['limit' => 30])
              ->addColumn('last_name', 'string', ['limit' => 30])
              ->addColumn('created', 'datetime')
              ->addColumn('updated', 'datetime', ['null' => true])
              ->addIndex(['username', 'email'], ['unique' => true])
              ->create();
    }
}
